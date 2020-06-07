import { Instruction } from './instruction';

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
    directions: Instruction[];
}
