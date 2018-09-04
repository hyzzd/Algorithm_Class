/**
 * Description
 * Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.
 * 
 * Examples
 * 1  --   2
 *     /   
 * 3  --   4
 * is bipartite (1, 3 in group 1 and 2, 4 in group 2).
 * 
 * 1  --   2
 *     /   |
 * 3  --   4
 * is not bipartite.
 * 
 * Assumptions
 * The graph is represented by a list of nodes, and the list of nodes is not null.
 * 
 * Time = O(n)
 * Space = O(n)
 */

public class Solution {
    // Assumptions: The given graph is not null
    public boolean isBipartite(List<GraphNode> graph) {
        // use 0 and 1 to denote two different groups.
        // the map maintains for rach node which group it belongs to.
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        // the graph can be represented by a list of nodes (if it is not guaranteed
        // to be connected). We have to do BFS from each of the nodes.
        // 一般只要一次bfs就可以了，但是如果不是连通图，就要所有的node都做一遍bfs
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, HashMap<GrapNode, Integer> visited) {
        // If this node has been traversed, no need to do BFS again.
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);
        // start breadth-first-search from the node, since the node has not been
        // visited, we can assign it to any of the groups, for example, group 0.
        visited.put(node, 0);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            // the group assigned for cur node
            int curGroup = visited.get(cur);
            // the group assigned for any neighbor of cur node
            int neiGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode nei : cur.neighbors) {
                // Case1: if the neighbor has not been visited, just put it in the
                // queue and choose the correct group.
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    // Case3: only if the neighbor has been traversed and the group
                    // does not match to the desired one, return false
                    return false;
                }
                // Case2: if the neighbor has been traversed and the group matches
                // the desired one, we do not need to do anything.
            }
        }
        return true;
    }
}