import { Ingredient } from 'app/delectable/recipe/model/ingredient';
import { Direction } from './instruction';

export class Recipe {
    id: number;
    name: string;
    source: string;
    description: string;
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
