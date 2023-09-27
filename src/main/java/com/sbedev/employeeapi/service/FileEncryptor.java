package com.sbedev.employeeapi.service;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

public class FileEncryptor {

	@Value( "${kycfront.aes.key}" )
	private String aesKeyBase64;

	private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final String AES_ALGORITHM = "AES";
	private static final int AES_KEY_SIZE = 2048; // 32 * 8 bits  
	private static final int IV_SIZE = 16; // bytes

	public ByteArrayOutputStream encryptFile( ByteArrayOutputStream byteArrayFile, SecretKey secretKey ) throws Exception {
		// Générer un vecteur d'initialisation (IV) pour le mode CBC
		byte[] iv = new byte[IV_SIZE];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes( iv );
		IvParameterSpec ivParameterSpec = new IvParameterSpec( iv );

		// Initialiser le chiffrement AES
		Cipher cipher = Cipher.getInstance( AES_TRANSFORMATION );
		cipher.init( Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec );

		byte[] encryptedData = cipher.doFinal( byteArrayFile.toByteArray() );

		// Combinez IV et les données chiffrées pour la sortie
		ByteArrayOutputStream combinedOutput = new ByteArrayOutputStream();
		combinedOutput.write( iv );
		combinedOutput.write( encryptedData );

		return combinedOutput;
	}

	public byte[] decryptFile( ByteArrayOutputStream encryptedByteArrayFile, SecretKey secretKey ) throws Exception {
		byte[] encryptedDataWithIv = encryptedByteArrayFile.toByteArray();

		// Extraire IV et données chiffrées
		byte[] iv = new byte[IV_SIZE];
		System.arraycopy( encryptedDataWithIv, 0, iv, 0, IV_SIZE );
		IvParameterSpec ivParameterSpec = new IvParameterSpec( iv );

		byte[] encryptedData = new byte[encryptedDataWithIv.length - IV_SIZE];
		System.arraycopy( encryptedDataWithIv, IV_SIZE, encryptedData, 0, encryptedDataWithIv.length - IV_SIZE );

		// Initialiser le déchiffrement AES
		Cipher cipher = Cipher.getInstance( AES_TRANSFORMATION );
		cipher.init( Cipher.DECRYPT_MODE, secretKey, ivParameterSpec );

		byte[] decryptedData = cipher.doFinal( encryptedData );

		return decryptedData;
	}

	public SecretKey generateAESKey_() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance( AES_ALGORITHM );
		keyGenerator.init( AES_KEY_SIZE );
		return keyGenerator.generateKey();
	}

	public SecretKey getAESKey() throws Exception {
		String aeskey = "OFNeogWHKoLxsfZAXvPqa1UHyawCmrhoH/JmLRBekNY=";
		byte[] decodedKey = Base64.getDecoder().decode( aeskey );
		//		.decode( aesKeyBase64 );

		return new SecretKeySpec( decodedKey, 0, decodedKey.length, AES_ALGORITHM );
	}

	//	public static void main( String[] args ) throws Exception {
	//		FileEncryptor encryptor = new FileEncryptor();
	//
	//		// Test
	//		ByteArrayOutputStream originalData = new ByteArrayOutputStream();
	//		originalData.write( "Hello, world!".getBytes() );
	//		SecretKey secretKey = encryptor.getAESKey();
	//
	//		ByteArrayOutputStream encryptedData = encryptor.encryptFile( originalData, secretKey );
	//		byte[] decryptedData = encryptor.decryptFile( encryptedData, secretKey );
	//
	//		System.out.println( new String( decryptedData ) );  // Should print "Hello, world!"
	//	}

	public static void main( String[] args ) {
		try {
			FileEncryptor encryptor = new FileEncryptor();

			// Générer la clé AES
			SecretKey secretKey = encryptor.getAESKey();

			// Lire le fichier PDF original
			String resourcePath = Paths.get( "src", "main", "resources" ).toString();
			InputStream is = new FileInputStream( resourcePath + "/doc.pdf" );
			//			InputStream is = this.getClass().getResourceAsStream( "/doc.pdf" );
			ByteArrayOutputStream originalData = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;
			while ( ( length = is.read( buffer ) ) != -1 ) {
				originalData.write( buffer, 0, length );
			}
			is.close();

			System.out.println( originalData.size() );

			// Chiffrer les données
			ByteArrayOutputStream encryptedData = encryptor.encryptFile( originalData, secretKey );
			FileOutputStream fosEncrypted = new FileOutputStream( resourcePath + "/encryptedData.pdf" );
			encryptedData.writeTo( fosEncrypted );
			fosEncrypted.close();

			// Déchiffrer les données
			byte[] decryptedData = encryptor.decryptFile( encryptedData, secretKey );
			FileOutputStream fosDecrypted = new FileOutputStream( resourcePath + "/decryptedData.pdf" );
			fosDecrypted.write( decryptedData );
			fosDecrypted.close();

			System.out.println( "Chiffrement et déchiffrement terminés!" );

		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
