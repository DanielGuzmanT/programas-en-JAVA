
package transformaciondecimal;
import java.io.*;
/**
 *
 * @author JHAIR
 */

//OBSERVACIONES: los numeros entregados no reciben periodicos puros o mixtos, por lo que
//               se le debe entregar numeros con tantos decimales como sea posible para 
//               un menor margen de error en el resultado

//POR MEJORAR:   trabajar con numeros periodicos puros y mixtos

public class TransformacionDecimal {

    public static void main(String[] args) throws IOException{
        
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Ingrese el numero a transformar: ");
        String numin = in.readLine();
        System.out.print("Ingrese la base del numero: ");
        int basein = Integer.parseInt(in.readLine());
        System.out.print("Ingrese la base a transformar: ");
        int basefin = Integer.parseInt(in.readLine());
  
        String[] partesnumero = hallarParteEnterayDecimal(numin);
            String nument = partesnumero[0];
            String numdec = partesnumero[1];
        
        int entero10 = convertiraBase10ent(basein, nument);
        double decimall0 = convertiraBase10dec(basein, numdec);
        
        String numfinent = convertiraBasePedidaEnt(basefin, entero10);
        String numfindec = convertiraBasePedidaDec(basefin, decimall0);
        
        String numfin = numfinent + "." + numfindec;
        
        System.out.println("\tEl numero " + numin + " en base " + basein + " es el numero " + numfin + " en base " + basefin);
    }
    
    //separar al numero de dato en parte entera y parte decimal
    public static String[] hallarParteEnterayDecimal (String numero){
        char letra;
        boolean puntoencontrado = false;
        String nument = "";
        String numdec = "";
        for (int i = 0; i < numero.length(); i++) {
            letra = numero.charAt(i);
            
            if (letra == '.'){
                puntoencontrado = true;
            }
            
            if (puntoencontrado == false) {
                nument = nument + letra;
            }else{
                if (letra =='.') {
                    numdec = numdec + "";
                }else{
                numdec = numdec + letra;
                }
            }  
        }
        String[] numeros = {nument, numdec};
        return numeros;
    }
    
    //convertir numero parte entera de base ingresada a base 10
    public static int convertiraBase10ent (int base, String numeroent){
        int numeroconv = 0;
        int codigo;
        int valor = 0;
            for (int i = 0; i < numeroent.length(); i++) {
                codigo = numeroent.codePointAt(i);
                
                if (codigo>64 && codigo<91) {
                    valor = codigo - 55;
                }else if (codigo > 47 && codigo < 57){
                    valor = codigo - 48;
                }       
                numeroconv = numeroconv*base + valor;
            }    
        return numeroconv;        
    }
    
    //convertir numero parte decimal de base ingresada a base 10
    public static double convertiraBase10dec (int base, String numerodec){
        double numeroconv = 0;
        int codigo;
        int valor = 0;
            for (int i = numerodec.length() - 1; i >=0 ; i--) {
                codigo = numerodec.codePointAt(i);
                
                if (codigo>64 && codigo<91) {
                    valor = codigo - 55;
                }else if (codigo > 47 && codigo < 57){
                    valor = codigo - 48;
                }       
                numeroconv = (numeroconv + valor) / base;
            }    
        return numeroconv;        
    }
    
    //convertir el numero parte entera de base 10 a base pedida
    public static String convertiraBasePedidaEnt(int base, int dividendo){
        String cadena = "";
        int residuo;
        char digito;
        do {
            residuo = dividendo % base;
            dividendo = dividendo / base;
            
            if (residuo > 9) {
                digito = (char)(residuo + 55);
                cadena = digito + cadena;
            }else{
                cadena = String.valueOf(residuo) + cadena;
            }
            
        } while (dividendo!=0);
        return cadena;
    }
    
    //convertir el numero parte decimal de base 10 a la base pedida
    public static String convertiraBasePedidaDec(int base, double decimal){
        int contador = 0;
        int parteentera;
        char digito;
        String cadena = "";
        
        do {
            decimal *= base;
            parteentera = (int)(decimal);
            decimal -= parteentera;
            
            //colocar caracter segun codigo ASCII
            if (parteentera > 9) {
                digito = (char)(parteentera + 55);
                cadena = cadena + digito;
            }else{
                cadena = cadena + String.valueOf(parteentera);
            }
            contador++;
        } while (decimal != 0.0 && contador < 20);
        return cadena;
    }
}