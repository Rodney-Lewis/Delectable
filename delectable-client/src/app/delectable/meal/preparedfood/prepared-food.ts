import { Direction } from '../instruction';
import { Meal } from '../meal';

export class PreparedFood extends Meal {
    brand: string;
    directions: Direction[];
}
