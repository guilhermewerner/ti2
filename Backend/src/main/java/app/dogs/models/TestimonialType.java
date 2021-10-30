package app.dogs.models;

/**
 * Abandono
 *
 * Agressões físicas, como: espancamento, mutilação, envenenamento;
 *
 * Manter o animal preso a correntes ou cordas;
 *
 * Manter o animal em locais não-arejados – sem ventilação ou entrada de luz;
 *
 * Manter o animal trancado em locais pequenos e sem o menor cuidado com a
 * higiene;
 *
 * Manter o animal desprotegido contra o sol, chuva ou frio;
 *
 * Não alimentar o animal de forma adequada e diariamente;
 *
 * Não levar o animal doente ou ferido a um veterinário;
 *
 * Submeter o animal a tarefas exaustivas ou além de suas forças;
 *
 * Utilizar animais em espetáculos que possam submetê-los a pânico ou estresse;
 *
 * Capturar animais silvestres;
 */

public enum TestimonialType {
    None, Abandonment, Aggression, Chaining, Hygiene, Sick;
}
