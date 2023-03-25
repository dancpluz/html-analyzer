package HtmlAnalyzer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        String result = analyzeHTML("http://hiring.axreng.com/internship/example1.html");
        System.out.println(result);
    }

    public static String analyzeHTML(String urlStr) {
    try {
        // Tenta extrair o código HTML do link dado
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2000); // Timeouts para evitar erros de conexão
        connection.setReadTimeout(2000);

        // BufferedReader para ler cada linha do código
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // Pilha de Árvores para gerar a Árvore completa
        Stack<TreeNode> stack = new Stack<>();
        // Pilha de tags anteriores para identificar erros de código html
        Stack<String> pastTags = new Stack<>();
        
        // Definição da raiz da árvore
        pastTags.push("html");
        TreeNode root = new TreeNode(reader.readLine());
        
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            int j = line.indexOf('<');
            if (j == -1) {
                root.children.add(new TreeNode(line));
                continue;
            }
            
            String fullTag = line;
            boolean closeTag = fullTag.charAt(1) == '/';
            String tag = closeTag ? fullTag.substring(2,fullTag.length()-1) : fullTag.substring(1,fullTag.length()-1);      
            
            if (closeTag) {
                if (!pastTags.peek().equals(tag)) return "malformed HTML";
                
                pastTags.pop();
                
                if (stack.isEmpty()) break;
                
                stack.peek().children.add(root);
                root = stack.peek();
                stack.pop();
            } else {
                pastTags.push(tag);
                stack.push(root);
                root = new TreeNode(fullTag);
            }
        }
        
        reader.close();
        
        if (!pastTags.isEmpty()) return "malformed HTML";
        
        return root.deepest().tagName;
        
    // Caso não seja possível conectar
    } catch (Exception e) {
        //e.printStackTrace(); // Por motivos do teste, os erros foram omitidos
        return "URL connection error"; 
        }
    }
}