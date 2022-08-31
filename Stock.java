import java.util.ArrayList;
import java.util.Scanner;
import java.sql.* ;
public class Stock {
    Connection con;
    ArrayList<Article> articles = new ArrayList<Article>();
    Scanner S = new Scanner(System.in);
    //constructeurs
    Stock()
    {
        System.out.println("constructeur par defaut stock");
    }
    void ajouterArticle()
    {
        try{
            Article A = new Article();
            articles.add(A);

            String sql = "INSERT INTO article (reference, designation, prixHT, tauxTVA) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, A.reference);
            statement.setString(2, A.designation);
            statement.setInt(3, A.prixHT);
            statement.setInt(4, A.tauxTVA);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new article was inserted successfully!");
            }

        }  catch (Exception e) {
            System.out.println("Erreur "+e);
            // gestion des exceptions
        }


    }
    void modifierArticle() 
    {
        try{
        System.out.println("reference Article :");
        String ref = S.next();
        Article A = new Article();
        String sql = "UPDATE article SET designation=?, prixHT=?,  tauxTVA=? WHERE reference=?";
 
    PreparedStatement statement = con.prepareStatement(sql);

    statement.setString(1, A.designation);
    statement.setInt(2,A.prixHT);
    statement.setInt(3,A.tauxTVA);
    statement.setString(4, ref);
 
    int rowsUpdated = statement.executeUpdate();
    if (rowsUpdated > 0) {
    System.out.println("An existing user was updated successfully!");
    }

}  catch (Exception e) {
    System.out.println("Erreur "+e);
    // gestion des exceptions
}

        
       
      
    } 
    void supprimerArticle()
    {

        try{
            System.out.println("reference Article :");
            String ref = S.next();

            String sql = "DELETE FROM Article WHERE reference=?";
    
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ref);
        
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An Article was deleted successfully!");
            }
            else
                System.out.println("Failed :(");
    
        }  catch (Exception e) {
            System.out.println("Erreur "+e);
            // gestion des exceptions
        }


    }
    void afficher()
    {
        System.out.println("designation reference prixHT tauxTVA PrixTTC");
        for(int i = 0 ; i<articles.size();i++)
        {
            articles.get(i).afficher();

        }
        
    }
    int menu(){
        System.out.println("**************************************");
        System.out.println("*bienvenue app gestion articles      *");
        System.out.println("1 : ajouter article");
        System.out.println("2 : modifier article");
        System.out.println("3 : supprimer article");
        System.out.println("4 : afficher articles");
        System.out.println("5 : quitter");
        System.out.println("**************************************");
        System.out.println("donner votre choix :");
        int choix = S.nextInt();
        return choix;
    }
    void makeConnection()
    {
        
        try {
            // Protocole de connexion
            String protocole =  "jdbc:mysql:" ;
             // Adresse IP de l’hôte de la base et port
            String ip =  "localhost" ;  // dépend du contexte
            String port =  "3306" ;  // port MySQL par défaut
             // Nom de la base ;
            String nomBase =  "stock" ;  // dépend du contexte
             // Chaîne de connexion
            String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
             // Identifiants de connexion et mot de passe
            String nomConnexion =  "root" ;  // dépend du contexte
            String motDePasse =  "" ;  // dépend du contexte
             // Connexion
            con = DriverManager.getConnection(
               conString, nomConnexion, motDePasse) ;
            
            

         }  catch (Exception e) {
            System.out.println("Erreur "+e);
             // gestion des exceptions
         }
        
       
    }
    
    public static void main (String args[])
    {
        int choix=0;
        
        Stock stock = new Stock();
        stock.makeConnection();
        do
        {
            choix = stock.menu();
            switch (choix){
                case 1 :
                    stock.ajouterArticle();
                    break;
                case 2 :
                    stock.modifierArticle();
                    break;
                case 3 :
                    stock.supprimerArticle();
                    break;
                case 4 :
                    stock.afficher();;
                    break;
                default :
                    break;
            }
        }while (choix != 5);
        
    }
    
}

