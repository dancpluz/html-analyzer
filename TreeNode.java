package HtmlAnalyzer;

import java.util.ArrayList;

// Estrutura simplificada de árvore não binária com a tag/texto da linha de html e um lista de filhos
public class TreeNode{
        String tagName;
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();

        TreeNode(String data){
            tagName = data;
        }
        
    // Função que retorna o ramo mais profundo da Árvore
    public TreeNode deepest() {
        // Listas para guardar a maior profundidade vista e a Árvore mais profunda
        int[] maxDepth = new int[]{0};
        TreeNode[] deepestNode = new TreeNode[]{null};
        
        // Chama a função recursiva para a raiz da Árvore, profundidade 0
        findDeepest(this, 0, maxDepth, deepestNode);
        return deepestNode[0];
    }
    
    // Função recursiva para passar pela Árvore e verificar a maior profundidade
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