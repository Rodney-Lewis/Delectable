export class Schedule {
    id:number;
    epoch:number;
    mealType:String;
    scheduledTypeId: number;
    scheduleType: String;

    //UI 
    epochDay:String;
    epochTime:String;
    uniqueDay:Boolean;
}
