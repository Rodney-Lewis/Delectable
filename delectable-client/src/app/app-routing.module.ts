import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecipeListComponent } from './component/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './component/recipe-form/recipe-form.component';
import { PantryFormComponent } from './component/pantry-form/pantry-form.component';
import { PantryListComponent } from './component/pantry-list/pantry-list.component';
import { RecipeForm2Component } from './component/recipe-form2/recipe-form2.component';
import { ScheduleListComponent } from './component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './component/schedule-form/schedule-form.component';
import { PantryDetailComponent } from './component/pantry-detail/pantry-detail.component';

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
  { path: 'recipe/add/2/:id', component: RecipeForm2Component }

];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }