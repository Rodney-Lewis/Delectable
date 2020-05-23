import { RecipeStep } from "./recipe-step";
import { Ingredient } from "../ingredient/ingredient";

export class Recipe {
    id: number;
    name: string;
    source: string;
    prepTimeHour: number;
    prepTimeMinute: number;
    prepTimeSecond: number;
    cookTimeHour: number;
    cookTimeMinute: number;
    cookTimeSecond: number;
    imageSource: string;
    //directions: RecipeStep[];
    //ingredients: Ingredient[];
}
