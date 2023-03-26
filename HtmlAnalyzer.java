import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

public class HtmlAnalyzer {
    public static void main(String[] args) throws IOException {
        String result = analyzeHTML(args[0]);
        System.out.println(result);
    }

    public static String analyzeHTML(String urlStr) {
    try {
        // Tenta extrair o código HTML do link dado
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000); // Timeouts para evitar erros de conexão
        connection.setReadTimeout(10000);

        // BufferedReader para ler cada linha do código
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // Pilha de árvores para gerar a árvore completa
        Stack<TreeNode> stack = new Stack<>();
        // Pilha de tags anteriores para identificar erros de código html
        Stack<String> pastTags = new Stack<>();
        
        // Definição da raiz da árvore
        pastTags.push("html");
        TreeNode root = new TreeNode(reader.readLine());
        
        // Loop em todas as linhas do html
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            int j = line.indexOf('<');
            
            // Caso a linha não tenha uma tag, adiciona com filho da raiz
            if (j == -1) {
                root.children.add(new TreeNode(line));
                continue;
            }
            
            String fullTag = line;
            // Formata a tag se for de abertura ou fechamento
            boolean closeTag = fullTag.charAt(1) == '/';
            String tag = closeTag ? fullTag.substring(2,fullTag.length()-1) : fullTag.substring(1,fullTag.length()-1);      
            
            // Se a tag for de abertura
            if (!closeTag) {
                // Adiciona na pilha de tags
                pastTags.push(tag);
                
                // Adiciona na pilha de árvores e cria uma nova raiz com a tag atual
                stack.push(root);
                root = new TreeNode(fullTag);
                
            // Se a tag for de fechamento
            } else {
                // Se o topo da pilha de tags não for igual a tag atual, erro (tag never opened or mismatched tags)
                if (!pastTags.peek().equals(tag)) return "malformed HTML";
                pastTags.pop();
                if (stack.isEmpty()) break;
                
                // Adiciona a raiz atual como filha da árvore do topo e muda a raiz para ela
                stack.peek().children.add(root);
                root = stack.peek();
                stack.pop();
            }
        }
        reader.close();
        // Se houver alguma tag ainda na pilha de tags, erro (tag never closed)
        if (!pastTags.isEmpty()) return "malformed HTML";
        return root.deepest().tagName;
        
    // Caso não seja possível conectar
    } catch (Exception e) {
        //e.printStackTrace(); // Por motivos do teste, os erros foram omitidos
        return "URL connection error"; 
        }
    }
}

// Estrutura simplificada de árvore não binária com a tag/texto da linha de html e uma lista de filhos
class TreeNode{
        String tagName;
        ArrayList<TreeNode> children = new ArrayList<>();

        TreeNode(String data){
            tagName = data;
        }
        
    // Função que retorna o ramo mais profundo da árvore
    public TreeNode deepest() {
        // Listas para guardar a maior profundidade vista e a árvore mais profunda
        int[] maxDepth = new int[]{0};
        TreeNode[] deepestNode = new TreeNode[]{null};
        
        // Chama a função recursiva para a raiz da árvore, profundidade 0
        findDeepest(this, 0, maxDepth, deepestNode);
        return deepestNode[0];
    }
    
    // Função recursiva para passar pela árvore e verificar a maior profundidade
    private void findDeepest(TreeNode node, int depth, int[] maxDepth, TreeNode[] deepestNode) {
        // Se o Node atual for mais profundo já visto, atualize
        if (depth > maxDepth[0]) {
            maxDepth[0] = depth;
            deepestNode[0] = node;
        }
        
        // Chamada recursiva para todos os filhos do Node
        for (TreeNode child : node.children) {
            findDeepest(child, depth + 1, maxDepth, deepestNode);
        }
    }
}