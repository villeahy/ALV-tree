/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package btree;

/**
 *
 * @author kamaj
 */
public class Menu {
//main alkaa-----------------------------------------------------------------------------
        public static void main(String[] args) {
        	printMenu();
        }
//main loppuu --------------------------------------------------------------------------
//printMenu alkaa------------------------------------------------------------------
        private static void printMenu() {
                char select;
                BinaryTree tree =new BinaryTree();
                String data;
                do {

                        System.out.println("\n\t\t\t1. Lisää avain.");
                        System.out.println("\t\t\t2. Etsi avaimella.");
                        System.out.println("\t\t\t3. Käy puu läpi esijärjestyksessä.");
                        System.out.println("\t\t\t4. Poista alkio.");
                        System.out.println("\t\t\t5. Katso alkion korkeus ");
                        System.out.println("\t\t\t6. Lopeta ");
                        System.out.print("\n\n"); // tehdään tyhjiä rivejä
                        select = Lue.merkki();
                        switch (select) {
                        case '1':
                            System.out.println("Anna uusi avain (merkkijono)");
                            data = new String(Lue.rivi());
                            tree.insert(data);
                            break;
                        case '2':
                                System.out.println("Anna etsittävä avain (merkkijono)");
                                data = Lue.rivi();
                                if (tree.find(data)!=null){
                                    System.out.println("Avain löytyi.");
                                }
                                else
                                    System.out.println("Avainta ei löytynyt.");

                            break;
                        case '3':
                            tree.preOrder();
                            char h = Lue.merkki(); // pysäytetään kontrolli
                            break;
                        case '4':
                        	System.out.println("Anna poistettava avain (merkkijono)");
                            data = Lue.rivi();
                            if (tree.find(data)!=null){
                                tree.delete(data);
                            }
                            else
                                System.out.println("Avainta ei löytynyt.");
                            break;
                        case '5':
                        	System.out.println("Anna mitattava avain (merkkijono)");
                            data = Lue.rivi();
                            if (tree.find(data)!=null){
                                System.out.println(tree.find(data).height());
                            }
                            else
                                System.out.println("Avainta ei löytynyt.");
                        	break;
                        case '6':
                        	break;
                        }
                }
                while (select != '6');
        }
//printMenu loppuu ----------------------------------------------------------------
}

