import { RecipeStep } from "./recipe-step";

export class Recipe {
    id: number;
    name: string;
    prepTime: number;
    cookTime: number;
    directions: RecipeStep[];
    ingredients: string[];
    source: string;
}
