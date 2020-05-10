import { Recipe } from '../recipe/recipe';

export class Schedule {
    id:number;
    epoch:number;
    mealType:String;
    recipe:Recipe;
    //UI 
    epochDay:String;
    epochTime:String;
    uniqueDay:Boolean;
}
