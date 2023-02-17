package entities;

public class Abonnement {
    private int id, type, id_produit, remise;

    public Abonnement(int type, int id_produit, int remise) {
        this.type = type;
        this.id_produit = id_produit;
        this.remise = remise;
    }

    public Abonnement(int id, int type, int id_produit, int remise) {
        this.id = id;
        this.type = type;
        this.id_produit = id_produit;
        this.remise = remise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    @Override
    public String toString() {
        return "Abonnement [id=" + id + ", type=" + type + ", id_produit=" + id_produit + ", remise=" + remise + "]";
    }

}
