import { RecipeStep } from "./recipe-step";
import { Ingredient } from "./ingredient";

export class Recipe {
    id: number;
    name: string;
    prepTime: number;
    cookTime: number;
    directions: RecipeStep[];
    ingredients: Ingredient[];
    source: string;
}
