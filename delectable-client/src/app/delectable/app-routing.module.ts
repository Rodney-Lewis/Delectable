import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { AuthGuard } from './user/route-guard/Auth.guard';
import { LoginComponent } from './user/component/login-page/login.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';
import { AdminConsoleComponent } from './user/component/admin-console/admin-console.component';
import { SignupComponent } from './user/component/signup/signup.component';
import { ProfileComponent } from './user/component/profile/profile.component';
import { AccountComponent } from './user/component/account/profile.component';
import { UserSettingsComponent } from './user/component/user-settings/user-settings.component';
import { ChangePasswordComponent } from './user/component/change-password/change-password.component';
import { Role } from './user/model/Role';
import { ComboFormComponent } from './combo/component/combo-form/combo-form.component';
import { ComboDetailComponent } from './combo/component/combo-detail/combo-detail.component';
import { ComboListComponent } from './combo/component/combo-list/combo-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminConsoleComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_ADMIN] } },
  { path: 'user/edit/:id', component: ProfileComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_ADMIN] } },
  { path: 'register', component: SignupComponent },
  {
    path: 'settings', component: UserSettingsComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_VEIWER] }, children: [
      { path: 'profile', component: AccountComponent },
      { path: 'security', component: ChangePasswordComponent },
      { path: '', redirectTo: 'profile', pathMatch: 'full' }
    ]
  },
  {
    path: 'schedule', children: [
      { path: 'add', component: ScheduleFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
    ]
  },
  {
    path: 'combo', children: [
      { path: 'add', component: ComboFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
      { path: 'edit/:id', component: ComboFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
      { path: 'detail/:id', component: ComboDetailComponent },
      { path: 'list', component: ComboListComponent },
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  },
  {
    path: 'restaurant', children: [
      { path: 'add', component: RestaurantFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
      { path: 'edit/:id', component: RestaurantFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
      { path: 'detail/:id', component: RestuarantDetailComponent },
      { path: 'list', component: RestaurantListComponent },
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  },
  {
    path: 'recipe', children: [
      { path: 'add', component: RecipeFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
      { path: 'edit/:id', component: RecipeFormComponent, canActivate: [AuthGuard], data: { role: Role[Role.ROLE_USER] } },
      { path: 'detail/:id', component: RecipeDetailComponent },
      { path: 'list', component: RecipeListComponent },
      { path: '', redirectTo: 'list', pathMatch: 'full' }
    ]
  },
  { path: '', component: ScheduleWeekAtAGlanceComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }