import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { PantryFormComponent } from './pantry/component/pantry-form/pantry-form.component';
import { PantryListComponent } from './pantry/component/pantry-list/pantry-list.component';
import { ScheduleListComponent } from './schedule/component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { PantryDetailComponent } from './pantry/component/pantry-detail/pantry-detail.component';

const routes: Routes = [
  { path: '', redirectTo:new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch:'full'},
  { path: ':epoch', component: ScheduleListComponent},
  { path: 'schedule/add', component: ScheduleFormComponent},
  { path: 'recipe/list', component: RecipeListComponent },
  { path: 'pantry/list', component: PantryListComponent },
  { path: 'recipe/detail/:id', component: RecipeDetailComponent },
  { path: 'pantry/detail/:id', component: PantryDetailComponent },
  { path: 'recipe/add', component: RecipeFormComponent },
  { path: 'pantry/add', component: PantryFormComponent },

];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }