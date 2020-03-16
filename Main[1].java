import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main {
  public static void main(String[] args) throws Exception {

    String st;
    
    int contLOC = 0;
    int contClass = 0;
    int contMetodos = 0;

    Path p = Paths.get("ola.txt");
		st = Files.readString(p, StandardCharsets.UTF_8);

    //Replaces que tiram todos os comentários, aspas e parêntesis do código
    st = st.replaceAll("\\\\\"","");
    st = st.replaceAll("\".*?\"", "");
		st = st.replaceAll("/\\*(.|[\r\n])*?\\*/", "");
		st = st.replaceAll("//.*", "");
    st = st.replaceAll("\\(.*?\\)"," ");

    String st2[] = st.split("\n");
    
    //For que conta LOC, classes e metodos
    for (int i = 0; i < st2.length;i++) {

      if(!(st2[i].trim().isEmpty())){
        contLOC++;
      }

      if((" "+st2[i]+" ").contains(" class ")){
        contClass++;
      }

      if(st2[i].contains("{")){
      contMetodos++;
      }
      if(st2[i].contains(" while ") || st2[i].contains(" for ") || st2[i].contains(" if ") || st2[i].contains(" switch ") || st2[i].contains(" else ") || st2[i].contains(" else if ") || st2[i].contains(" do ") || st2[i].contains(" try ") || st2[i].contains(" catch ") ){
          contMetodos--;
      }
      
    }
    System.out.println("LOC: " + contLOC);
    System.out.println("Classes: " + contClass);
    System.out.println("Metodos: " + (contMetodos - contClass));
  }
}