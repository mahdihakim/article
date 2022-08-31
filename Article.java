import java.util.Scanner;

public class Article {
    //declaration des attributs
    String reference;
    int prixHT ;
    String designation;
    static int tauxTVA = 19;
    Scanner myobj = new Scanner(System.in);

    //constructeurs
    Article(){

        System.out.println("entrez la reference:");
        reference = myobj.next();

        System.out.println("entrez le designation:");
        designation = myobj.next();

        System.out.println("entrez le prixHT:");
        prixHT = myobj.nextInt();

    }
    Article(String d , int p , String r){
        prixHT = p;
        reference = r;
        designation = d;
    }
    Article(String d , String r){
        reference = r;
        designation = d;
    }
    Article(Article x){
        prixHT = x.prixHT;
        reference = x.reference;
        designation = x.designation;
    }
    //declaration des methodes
    double CalculerPrixTTC() {
        return( prixHT +(prixHT*tauxTVA/100));
    }
    void afficher(){
        System.out.println(designation +" "+ reference +" "+ prixHT +" "+ tauxTVA +" "+ CalculerPrixTTC());
    }
    void modifier(){
        System.out.println("entrez la nouvelle reference:");
        reference = myobj.next();

        System.out.println("entrez la nouvelle  designation:");
        designation = myobj.next();

        System.out.println("entrez le nouveau prixHT:");
        prixHT = myobj.nextInt();

    }
    public static void main(String[] args) {
        Article A= new Article();
        A.afficher();

    }
}