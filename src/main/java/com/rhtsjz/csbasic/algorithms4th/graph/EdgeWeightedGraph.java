package com.rhtsjz.csbasic.algorithms4th.graph;


import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by zsj on 16-8-27.
 */
public class EdgeWeightedGraph {
    private final int V; //顶点总数
    private int E; //边总数
    private Bag<Edge>[] adj; //邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this.V = 0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph;
        graph = new EdgeWeightedGraph(in);

    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adg(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        return null;
    }
}
