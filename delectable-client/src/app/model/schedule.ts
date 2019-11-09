import { Recipe } from './recipe';

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
