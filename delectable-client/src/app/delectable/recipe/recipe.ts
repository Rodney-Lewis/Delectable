import { RecipeStep } from "./recipe-step";
import { Ingredient } from "../ingredient/ingredient";

export class Recipe {
    id: number;
    name: string;
    prepTime: string;
    cookTime: string;
    totalTime: string;
    directions: RecipeStep[];
    ingredients: Ingredient[];
    source: string;
    imageSource: string;
}
