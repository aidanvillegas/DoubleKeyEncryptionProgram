
import java.util.Scanner;
import java.util.ArrayList;
//MAIN
class Main 

//Example is (exponent 3, prime number 2561, GOLDMEDALQ, returns 2120182579819382488)

  {
    //GLOBAL DECLERATIONS
    public static int primeNum2;
    public static Scanner sc = new Scanner(System.in);
    public static int s;
    public static int x;
    public static String finalD;
    public static boolean deBool=false;
    public static String finalEncryptedMessage;
    public static ArrayList<String> nums = new ArrayList<String>();
    public static String encryptUser;
    public static String decryptedM;
    //MAIN FUNCTION
    public static void main(String[] args) 
      {
        //PROGRAM INTRO
        System.out.println("\n............................\nDual Key RSA Encryption!\n");
        System.out.println("Enter a prime number 'e' (this number is your exponent) to generate keys: ");
        int primeNum1=sc.nextInt(); //FOR INPUT
        while(!primeO(primeNum1))
          {
           System.out.println("THAT WASN'T PRIME! Enter a prime number 'e' (this number is your exponent) to generate keys: ");
           primeNum1=sc.nextInt(); //FOR INPUT
          }
        System.out.println(primeNum1 + " is prime!");
        System.out.println("Enter another number 'n' (this number is your mod) to generate keys: ");
        primeNum2=sc.nextInt(); //FOR INPUT
        System.out.println("Enter a message (NO SPACES, EVEN # of digits) to encrypt: ");
        sc.nextLine(); //TO CLEAR IO
        encryptUser=sc.next();
        encryptUser=encryptUser.toUpperCase(); //FOR ASCII
        toArrayList(encryptUser); //SEPERATES WORD INTO ARRAY LIST
        System.out.println("ASCII LIST IS: " + nums);
        //ENCRYPTION
        encrypt(primeNum1,primeNum2,encryptUser);
        //FIRST NUMBER IS EXPONENT, SECOND IS MOD, THIRD IS PLAINTEXT
        //System.out.print("ASCII LIST IS: " + nums);
        //DECRYPTION
        decrypt(finalEncryptedMessage);

        sc.close(); //CLOSES SCANNER

      }

      //RNG 

          /*
          public static int  generateKeyP()
          {
            int MIN = 50;
            int MAX = 10000;
            //for (int i = 0; i < 10; i++) 
            
              return((int) (Math.random() * ((MAX - MIN) + 1)) + MIN);
            
          }
          public static int  generateKeyE()
          {
            int MIN = 2;
            int MAX = 99;
            //for (int i = 0; i < 10; i++) 
            
              return((int) (Math.random() * ((MAX - MIN) + 1)) + MIN);
            
          }
          */
    //HELPER FUNCTIONS
    public static int gcd(int a, int b) //EASY GCD FUNCTION
      {
        if (b==0) return a;
        return gcd(b,a%b);
      }
    public static int toNum (int a) //TO ASCII DECIMAL
      {
        return a-65;
      }
    public static int toChar (int a) //TO ASCII SYMBOL
      {
        return a+65;
      }

    //FUNCTION TO SEPERATE STR INTO 4 digit number blocks
    public static void toArrayList (String a)
      {
        finalD="";
        if(!deBool)
          {
            //System.out.println("correct");
            if(a.length()%2==0)
              {
                int i=0;
                while(i<a.length())
                  {
                    s=(toNum(a.charAt(i)));
                    x=(toNum(a.charAt(i+1)));
                    String str = String.format("%02d", s);
                    String str1 = String.format("%02d", x);
                    String tempS=str+str1;
                    //System.out.print(str+" " +str1 +" " + tempS);
                    nums.add(tempS);
                    i=i+2;
                  }
                }
            else
              {
                for(int j=0; j<a.length()-2;++j)
                  {
                    s=(toNum(a.charAt(j)));
                    x=(toNum(a.charAt(j+1)));
                    String str = String.format("%02d", s);
                    String str1 = String.format("%02d", x);
                    String tempS=str+str1;
                    //System.out.print(str+" " +str1 +" " + tempS);
                    nums.add(tempS);
                      ++j;
                  }
  
                s=(toNum(a.charAt(a.length()-1)));
                String str = String.format("%02d", s);
                finalD=finalD+str;
                //nums.add(str);
              }
          }
        else //FOR DECRYPT
          {
            int i=0;
            while(i<a.length())
              {
                if(4<(a.length()-i))
                  {

                  
                  // s=(Integer.valueOf((a.charAt(i))));
                  // x=(Integer.valueOf((a.charAt(i+1))));
                  //String str = String.format("%02d", a.charAt(i));
                  //String str1 = String.format("%02d", a.charAt(i+1));
                    //System.out.println("i is: " +i);
                    String tempS=a.substring(i,i+4);
                    //System.out.println("num list: a.sub is" + tempS);
                    //System.out.print(str+" " +str1 +" " + tempS);
                    nums.add(tempS);
                    i=i+4;
                  }
                else
                  {
                    if(i<=a.length())
                      {
                        //System.out.println("OH MY");
                        String tempS=a.substring(i,i+a.length()-i);
                        nums.add(tempS);
                        i=a.length();
                      }
                  }
              }
          }
      }
      
    public static void decrypt(String message)
      {
        deBool=true;
        System.out.println("Enter a prime number (this number is your exponent) to generate keys: ");
        int primeNum1=sc.nextInt();
        //System.out.println("Enter another prime number (this number is your mod) to generate keys: ");
        //int primeNum2=sc.nextInt();
        while(!primeO(primeNum1))
          {
           System.out.println("THAT WASN'T PRIME! Enter a prime number 'p' (this number is your exponent) to generate keys: ");
           primeNum1=sc.nextInt(); //FOR INPUT
          }
        System.out.println(primeNum1 + " is prime!");
        System.out.println("Encrypted Message is: '" + message + "' . \nDecrypting......");
        //System.out.println("TOARRAYB");
        toArrayList(message);
        //System.out.println("TOARRAYA");
        int tempInt;
        String DecryptedM = "";
        String tempString1;
        for (int i = 0; i< nums.size(); ++i)
          {
              //System.out.println("MESSAGE IS: " + nums.get(i));
              //System.out.println("FORLOOP1");
              tempInt = Integer.valueOf(nums.get(i));
              System.out.println("Current Array List is: \n" + nums +"\nCurrently looking at: " + nums.get(i) + "\nTEMPINT IS: " + tempInt) ;
              //System.out.println("FORLOOP2");
              tempInt = (int) Math.pow(tempInt,primeNum1);
              System.out.println("Raising "+nums.get(i) + " to the power of " + primeNum1 +" .\ntempInt is: " + tempInt) ;
              //System.out.println("FORLOOP3");
              System.out.println("tempInt is: " +tempInt + " and primeNum2 is: " + primeNum2 + " . Modding "+tempInt + " by " + primeNum2 );
              tempInt = tempInt % primeNum2;
              System.out.println(" .\ntempInt is: " + tempInt) ;
              //System.out.println("FORLOOP4");
              tempString1=String.valueOf((tempInt));
              
              //System.out.println("FORLOOP5");
              DecryptedM += tempString1;
              System.out.println("DECRYPTED M: " +DecryptedM );
              System.out.println("\n\n");
              //System.out.println("FORLOOP6; decryptedM is: " + DecryptedM + " and i is: " +i);
              tempInt=0;
              
          }
        //System.out.println(nums);
        nums.clear();
        System.out.println("\nCONVERTING DECI TO ASCII...\n\nDecrypted Message is: " + decryptedM );
        //int decryptedMessage = (int) Math.pow(Integer.valueOf(message),primeNum1);
        //decryptedMessage = decryptedMessage % primeNum2;
        //toArrayList(String.valueOf(decryptedMessage));
        //String decryptedMM = finalD;
        //System.out.println("Decrypted message is: " + decryptedMM + ".");
        deBool=false;
      }

        public static void encrypt (int key2, int key1, String message)
          {
            //int message1 = Integer.valueOf(message);
            System.out.println("Starting encryption. Message is: '" + message +"' . mod is: " + key1 + ". Exponent is: " + key2 +".");
            decryptedM=message; 
            String encryptedM = "";
            int tempInt;
            for (int i = 0; i< nums.size(); ++i)
              {
                tempInt = Integer.valueOf(nums.get(i));
                System.out.println("Raising "+tempInt + " to the power of " + key2 +" .") ;
                tempInt = (int) Math.pow(tempInt,key2);
                //System.out.println("step2" ) ;
                //System.out.println("key1:" + key1) ;
                //System.out.println("tempInt:" + tempInt) ;
                System.out.println("Modding "+tempInt + " by " + key1 +" .") ;
                tempInt = tempInt % key1;
                //System.out.println("step3" ) ;
                //System.out.println("tempInt:" + tempInt) ;
                encryptedM += String.valueOf(tempInt);
                //System.out.println("step4" ) ;
                System.out.println("Message is currently: " + encryptedM);
              }
            //System.out.println("ASCII LIST IS: " + nums);
            nums.clear();
            System.out.println("\n\nEncrypted Message is: " + encryptedM);
            finalEncryptedMessage=encryptedM;
          }
        //https://www.javatpoint.com/prime-number-program-in-java
    public static boolean primeO(int n){    
      int i,m=0,flag=0;      
      //int n;//it is the number to be checked    
      m=n/2;      
      if(n==0||n==1){  
       System.out.println(n+" is not prime number");      
      }else{  
       for(i=2;i<=m;i++){      
        if(n%i==0){      
         System.out.println(n+" is not prime number");      
         flag=1;      
         break;      
        }      
       }      
       if(flag==0)  { 
         return true;
         }  
      }//end of else  
      return false;
    } 
  }