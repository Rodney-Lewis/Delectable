import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { ScheduleCalendarComponent } from './schedule/component/schedule-calendar/schedule-calendar.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';

const routes: Routes = [
  { path: '', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' },
  { path: ':epoch', component:  ScheduleCalendarComponent},
  { path: 'schedule/add', component: ScheduleFormComponent },
  { path: 'recipe/add', component: RecipeFormComponent },
  { path: 'recipe/edit/:id', component: RecipeFormComponent },
  { path: 'recipe/detail/:id', component: RecipeDetailComponent },
  { path: 'recipe/list', component: RecipeListComponent },
  { path: 'pantry/edit/:id', component: RecipeFormComponent },
  { path: 'restaurant/list', component: RestaurantListComponent },
  { path: 'restaurant/add', component: RestaurantFormComponent },
  { path: 'restaurant/edit/:id', component: RestaurantFormComponent },
  { path: 'restaurant/detail/:id', component: RestuarantDetailComponent },
  { path: '**', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }