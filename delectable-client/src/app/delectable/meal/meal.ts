import { Direction } from './instruction';
import { Ingredient } from '../ingredient/ingredient';

export class Meal {
    id: number;
    name: string;
    prepTimeHour: number;
    prepTimeMinute: number;
    prepTimeSecond: number;
    cookTimeHour: number;
    cookTimeMinute: number;
    cookTimeSecond: number;
    imageSource: string;
    deleted: boolean;
    description: string;
}
