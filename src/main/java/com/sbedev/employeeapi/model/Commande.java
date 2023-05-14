package com.sbedev.employeeapi.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long  serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcmd")
    private Long id;
    @Column(name = "datecmd")
    private LocalDate dateCommande;
    @ManyToOne
    @JoinColumn(name = "client_idclient", referencedColumnName = "idclient")
    @JsonBackReference
    private Client client;
    @ManyToOne
    @JoinColumn(name = "status_idcmdstatus", referencedColumnName = "idcmdstatus")
    private CommandeStatus status;

    public CommandeStatus getStatus() {
        return status;
    }

    public void setStatus(CommandeStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commande)) return false;
        Commande commande = (Commande) o;
        return getId().equals(commande.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
