package com.sbedev.employeeapi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfAConformanceLevel;
import com.itextpdf.kernel.pdf.PdfBoolean;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfOutputIntent;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfResources;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.xmp.XMPException;
import com.itextpdf.pdfa.PdfADocument;

public class PdftoPdfa {


	private void convertToPDFa( InputStream input, ByteArrayOutputStream output ) throws IOException, XMPException {
		PdfReader reader = new PdfReader( input );
		PdfWriter writer = new PdfWriter( output );

		/*
		 * L'intention de sortie définit les caractéristiques de couleur du document. Cela garantit que les couleurs utilisées dans le document sont
		 * interprétées correctement lorsqu'il est visualisé ou imprimé.
		 * 
		 * sRGB_CS_profile.icm: Utilisez un profil de couleur sRGB qui est généralement utilisé pour les documents destinés à être visualisés sur des écrans.
		 */

		PdfOutputIntent pdfOutputIntent = new PdfOutputIntent( "Custom",
															   "",
															   null,
															   "sRGB IEC61966-2.1",
															   getClass().getResourceAsStream( "/sRGB_CS_profile.icm" ) );

		/*
		 * Commencez par créer un nouveau document PDF/A. PDF/A est un standard ISO pour l'archivage électronique à long terme des documents
		 * électroniques.
		 * <p>
		 * 
		 * ## Les niveaux de conformité PDF/A
		 * 
		 * PDF_A_1A: garantit à la fois la préservation à long terme et l'accessibilité des documents.
		 * PDF_A_1B: garantit la conservation visuelle à long terme des documents PDF, sans se concentrer sur l'accessibilité.
		 * PDF/A-2A: assure la préservation à long terme et l'accessibilité des documents tout en prenant en compte les améliorations PDF telles que les annotations, la transparence, la compression JPEG2000, etc.
		 * PDF/A-2B: garantit la conservation visuelle à long terme, comme PDF/A-1B, mais avec les améliorations de la norme PDF 1.7.
		 * PDF/A-2U: garantit la conservation visuelle et assure que le texte du document est Unicode, ce qui facilite la recherche de texte et sa copie.
		 * PDF/A-3A: permet l'incorporation de fichiers de toute sorte et type (par exemple XML, CSV, CAD, images, tableaux, sons) et garantit aussi la préservation à long terme et l'accessibilité des documents.
		 * PDF/A-3B: comme PDF/A-3A, mais sans les exigences d'accessibilité; permet également l'incorporation de fichiers de tout type.
		 * PDF/A-3U: combine les caractéristiques de PDF/A-2U et PDF/A-3B. Garantit la conservation visuelle, s'assure que le texte est Unicode et permet d'incorporer des fichiers de tout type.
		 * 
		 * mais qui exige que le texte principal du document soit entièrement balisé pour assurer l'accessibilité.
		 * </p>
		 */

		PdfADocument pdfADocument = new PdfADocument( writer, PdfAConformanceLevel.PDF_A_2A, pdfOutputIntent );

		/*
		 * Marquez le document comme "balisé", ce qui est nécessaire pour la conformité PDF/A. 
		 * Le balisage fournit une structure logique au document qui décrit la relation entre les différents éléments, 
		 * comme les paragraphes, les titres, les images, etc. C'est important pour l'accessibilité.
		 */
		pdfADocument.setTagged();

		// Définissez la langue du document comme étant l'anglais (US).
		//		pdfADocument.getCatalog().setLang( new PdfString( "en-US" ) );

		/*
		 *  Définissez les préférences pour les personnes qui visualisent le document.
		 * Ici, nous indiquons que le titre du document doit être affiché lorsqu'il est ouvert dans un lecteur PDF.
		 */
		pdfADocument.getCatalog().setViewerPreferences( new PdfViewerPreferences().setDisplayDocTitle( true ) );

		PdfDocument pdfDoc = new PdfDocument( reader );
		int pageCount = pdfDoc.getNumberOfPages();

		PdfPage page;
		for ( int i = 1; i <= pageCount; i++ ) {

			PdfPage originalPage = pdfDoc.getPage( i );
			PageSize size = new PageSize( originalPage.getPageSize() );
			page = pdfADocument.addNewPage( size );

			PdfResources resources = originalPage.getResources();
			PdfDictionary xObjects = resources.getPdfObject().getAsDictionary( PdfName.XObject );
			if ( xObjects != null ) {
				for ( PdfName name : xObjects.keySet() ) {
					PdfStream xObjectStream = xObjects.getAsStream( name );
					if ( PdfName.Image.equals( xObjectStream.getAsName( PdfName.Subtype ) ) ) {
						xObjectStream.put( PdfName.Interpolate, PdfBoolean.FALSE );
					}
				}
			}

			PdfCanvas pdfCanvas = new PdfCanvas( page );
			pdfCanvas.addXObject( originalPage.copyAsFormXObject( pdfADocument ) );
		}

		pdfDoc.close();
		pdfADocument.close();
		reader.close();
	}

	/**
	 * itextpdf v5
	 */
	//	private void convertToPDFa( InputStream input, ByteArrayOutputStream output ) throws  IOException {
	//		// Créer un document PDF
	//		Document document = new Document();
	//
	//		// Instancier un PdfAWriter avec le document, la sortie, et le niveau de conformité PDF/A-1B
	//		PdfAWriter writer = PdfAWriter.getInstance( document, output, PdfAConformanceLevel.PDF_A_1B );
	//
	//		// Créer des métadonnées XMP nécessaires pour la conformité PDF/A
	//		writer.createXmpMetadata();
	//
	//		// Activer le balisage du document pour la conformité PDF/A
	//		writer.setTagged();
	//
	//		// Définir la version du PDF à 1.4, qui est nécessaire pour la conformité PDF/A-1B
	//		writer.setPdfVersion( PdfWriter.VERSION_1_4 );
	//
	//		// Ouvrir le document pour l'écriture
	//		document.open();
	//
	//		// Créer un lecteur PDF
	//		PdfReader reader = new PdfReader( input );
	//
	//		// Obtenir le nombre de pages du PDF d'origine
	//		int pageCount = reader.getNumberOfPages();
	//
	//		// Parcourir chaque page du PDF d'origine
	//		for ( int i = 1; i <= pageCount; i++ ) {
	//			// Ajouter une nouvelle page au document PDF/A
	//			document.newPage();
	//
	//			// Obtenir le contenu de la page du PDF d'origine
	//			PdfImportedPage page = writer.getImportedPage( reader, i );
	//
	//			// Ajouter la page au document PDF/A
	//			writer.getDirectContent().addTemplate( page, 0, 0 );
	//		}
	//
	//		// Fermer le document PDF
	//		document.close();
	//	}
}
