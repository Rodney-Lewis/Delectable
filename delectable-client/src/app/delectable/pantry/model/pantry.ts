import { Direction } from 'app/delectable/recipe/model/instruction';

export class PantryItem {
    id: number;
    name: string;
    directions: Direction[];
    imageSource: string;
    deleted: boolean;
}
