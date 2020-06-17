import { Ingredient } from 'app/delectable/ingredient/ingredient';
import { Meal } from '../meal';
import { Direction } from '../instruction';

export class Recipe extends Meal{
    source: string;
    directions: Direction[];
    ingredients: Ingredient[];
}
