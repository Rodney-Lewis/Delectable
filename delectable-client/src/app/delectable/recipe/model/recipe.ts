import { Direction } from './direction';
import { Ingredient } from './ingredient';

export class Recipe {
    id: number;
    name: string;
    source: string;
    description: string;
    servings: number;
    prepTimeHour: number;
    prepTimeMinute: number;
    prepTimeSecond: number;
    cookTimeHour: number;
    cookTimeMinute: number;
    cookTimeSecond: number;
    ingredients: Ingredient[];
    directions: Direction[];
    imageSource: string;
    deleted: boolean;
}
