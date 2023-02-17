package entities;

public class Livraison {
    private int id, id_transporteur, prix, id_stock;

    public Livraison(int id, int id_transporteur, int prix, int id_stock) {
        this.id = id;
        this.id_transporteur = id_transporteur;
        this.prix = prix;
        this.id_stock = id_stock;
    }

    public Livraison(int id_transporteur, int prix, int id_stock) {
        this.id_transporteur = id_transporteur;
        this.prix = prix;
        this.id_stock = id_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_transporteur() {
        return id_transporteur;
    }

    public void setId_transporteur(int id_transporteur) {
        this.id_transporteur = id_transporteur;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    @Override
    public String toString() {
        return "Livraison [id=" + id + ", id_transporteur=" + id_transporteur + ", prix=" + prix + ", id_stock="
                + id_stock + "]";
    }

}
