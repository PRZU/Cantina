package com.example.dwp46.cants.Helpers;

import java.util.HashMap;

/**
 * Created by cruz-HP on 25/02/2018.
 */

class TakeAwayEmenta
{
    private Map_Ementa map_ementa = new Map_Ementa();

    HashMap<String, String> getTakeAwayEmenta()
    {
        return map_ementa.getEmenta();
    }

    public static class Map_Ementa
    {
        HashMap<String, String> ementa = null;

        HashMap<String, String> getEmenta()
        {
            return ementa;
        }

        private Map_Ementa()
        {
            HashMap<String, String> ementa = new HashMap<>();
            ementa.put("Arroz de pato", "3,50 €");
            ementa.put("Bacalhau com natas", "3,50 €");
            ementa.put("Coelho com tomate e tomilho", "5,00 €");
            ementa.put("Bacalhau à Brás", "4,50 €");
            ementa.put("Coxas de frango com arroz à moda d'avó", "3,00 €");
            ementa.put("Bacalhau à Zé do Pipo", "5,00 €");
            ementa.put("Coxas de frango com caril", "3,00 €");
            ementa.put("Bacalhau com broa e bacon", "5,00 €");
            ementa.put("Crepe de Frango", "3,50 €");
            ementa.put("Bacalhau espiritual", "4,50 €");
            ementa.put("Empadão de carne", "3,50 €");
            ementa.put("Cannelloni de pescada", "3,50 €");
            ementa.put("Feijoada à transmontana", "4,00 €");
            ementa.put("Crepes de bacalhau com espinafres", "3,50 €");
            ementa.put("Gratinado de Frango", "4,00 €");
            ementa.put("Feijoada do mar", "4,00 €");
            ementa.put("Hambúrguer à Grill", "3,50 €");
            ementa.put("Filetes de pangasius dourados", "3,00 €");
            ementa.put("Lasanha de frango com legumes", "3,50 €");
            ementa.put("Filetes de pescada dourados", "3,50 €");
            ementa.put("Lombo de porco assado", "3,50 €");
            ementa.put("Folhado de pescada com legumes", "4,00 €");
            ementa.put("Massa à Bolonhesa", "3,00 €");
            ementa.put("Massa à bolonhesa", "3,00 €");
            ementa.put("Lasanha de atum", "4,00 €");
            ementa.put("Massa à Carbonara", "3,00 €");
            ementa.put("Massa à carbonara", "3,00 €");
            ementa.put("Massa com camarão e delícias", "4,00 €");
            ementa.put("Massa com peru e legumes", "3,00 €");
            ementa.put("Massa gratinada com atum", "3,50 €");
            ementa.put("Migas de bacalhau com broa", "4,50 €");
            ementa.put("Pescada à Brás", "3,00 €");
            ementa.put("Pescada à Provençal", "4,00 €");
            ementa.put("Strogonoff de peru", "3,50 €");
            ementa.put("Pescada com broa", "4,00 €");
            ementa.put("Rancho", "3,50 €");
            ementa.put("Pescada gratinada", "4,00 €");
            ementa.put("Rojões à moda do Minho", "4,50 €");
            ementa.put("Salmão gratinado c/ legumes", "4,50 €");
            ementa.put("Tranches de vitela estufadas", "5,00 €");
            ementa.put("Tentáculos pota estufados c/ legumes", "4,00 €");
            ementa.put("Tortilha de bacalhau", "3,50 €");
            ementa.put("Tortilha do mar", "3,00 €");
            ementa.put("Peito de Peru assado com ervas aromáticas e vinho do Porto c/ arroz de frutos secos com açafrão", "5,00 €");
            ementa.put("Sopa", "0,75 €");
            ementa.put("Vegetariano", "4,00 €");

            //Desconhecidos
            ementa.put("Massa com salmão e camarão", "?");
            ementa.put("Vitelão assado com alecrim", "?");
            //Sopas
            ementa.put("Creme de ervilhas", "0,75 €");
            ementa.put("Sopa juliana", "0,75 €");
            ementa.put("Sopa de brócolos", "0,75 €");
            ementa.put("Creme de feijão vermelho c/ abóbora", "0,75 €");
            ementa.put("Sopa de nabiças", "0,75 €");
            ementa.put("Creme de abóbora", "0,75 €");
            ementa.put("Sopa de feijão vermelho e verde", "0,75 €");
            ementa.put("Sopa camponesa", "0,75 €");
            ementa.put("Sopa de feijão branco c|/ penca", "0,75 €");
            ementa.put("Sopa de alho-francês", "0,75 €");
            ementa.put("Sopa de feijão branco c/ couve", "0,75 €"); //branca
            ementa.put("Sopa de feijão branco c/ couve branca", "0,75 €"); // ??
            ementa.put("Sopa de feijão vermelho", "0,75 €");
            ementa.put("Sopa de couve branca e cenoura", "0,75 €");
            ementa.put("Sopa de feijão verde", "0,75 €");
            ementa.put("Creme de legumes", "0,75 €");
            ementa.put("Sopa de couve flor", "0,75 €");
            ementa.put("Sopa de grão", "0,75 €");

            //Veget
            ementa.put("Gratinado de legumes c/ cogumelos", "4,00 €");
            ementa.put("Espetadinhas de tofu c/ legumes", "4,00 €");
            ementa.put("Bifinhos de seitan com molho de", "4,00 €"); // cogumelos
            ementa.put("Gratinado de tofu c/legumes", "4,00 €");
            ementa.put("Pataniscas de algas", "4,00 €");
            ementa.put("Tofu salteado com brócolos", "4,00 €");
            ementa.put("Bolonhesa de seitan", "4,00 €");
            ementa.put("Chili de seitan", "4,00 €");
            ementa.put("Feijoada de algas", "4,00 €");
            ementa.put("Cuscuz com legumes e grão de bico", "4,00 €");
            ementa.put("Beringela recheada c/ soja", "4,00 €");
            ementa.put("Pataniscas de alho francês", "4,00 €");
            ementa.put("Lasanha de soja", "4,00 €");
            ementa.put("Espetadinhas de Tofu com Legumes", "4,00 €");
            ementa.put("Gratinado de legumes c/ cogumelos", "4,00 €");
            ementa.put("Esparguete à bolonhesa de soja", "4,00 €");
            ementa.put("Curgete recheada c/seitan", "4,00 €");
            ementa.put("Lasanha de legumes c/ cogumelos", "4,00 €");
            ementa.put("Bolonhesa de seitan", "4,00 €");
            ementa.put("Jardineira de seitan", "4,00 €");



            this.ementa = ementa;
        }
    }
}