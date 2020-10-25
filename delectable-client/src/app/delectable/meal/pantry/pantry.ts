import { Direction } from '../_shared/direction';

export class PantryItem {
    id: number;
    name: string;
    description: string;
    servings: number;
    prepTimeHour: number;
    prepTimeMinute: number;
    prepTimeSecond: number;
    cookTimeHour: number;
    cookTimeMinute: number;
    cookTimeSecond: number;
    directions: Direction[];
    imageSource: string;
    deleted: boolean;
}
