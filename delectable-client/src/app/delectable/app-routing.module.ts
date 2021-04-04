import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { YourGuardGuard } from './user/path_gaurd/your-guard.guard';
import { LoginComponent } from './user/component/login-page/login.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';
import { AdminConsoleComponent } from './user/component/admin-console/admin-console.component';
import { SignupComponent } from './user/component/signup/signup.component';
import { ProfileComponent } from './user/component/profile/profile.component';
import { AccountComponent } from './user/component/account/profile.component';
import { UserSettingsComponent } from './user/component/user-settings/user-settings.component';
import { ChangePasswordComponent } from './user/component/change-password/change-password.component';
import { Role } from './user/model/Role';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'settings', component: UserSettingsComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_VEIWER] }, children: [
      { path: 'profile', component: AccountComponent },
      { path: 'security', component: ChangePasswordComponent },
      { path: '', redirectTo: 'profile', pathMatch: 'full' }
    ]
  },
  { path: 'admin', component: AdminConsoleComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_ADMIN] } },
  { path: 'user/edit/:id', component: ProfileComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_ADMIN] } },
  { path: 'account/:id', component: AccountComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_ADMIN] } },
  { path: 'register', component: SignupComponent },
  { path: '', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' },
  { path: ':epoch', component: ScheduleWeekAtAGlanceComponent, data: { role: Role[Role.ROLE_USER] } },
  { path: 'schedule/add', component: ScheduleFormComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_USER] } },
  { path: 'recipe/add', component: RecipeFormComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_USER] } },
  { path: 'recipe/edit/:id', component: RecipeFormComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_USER] } },
  { path: 'recipe/detail/:id', component: RecipeDetailComponent },
  { path: 'recipe/list', component: RecipeListComponent, data: { role: Role[Role.ROLE_USER] } },
  { path: 'pantry/edit/:id', component: RecipeFormComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_USER] } },
  { path: 'restaurant/list', component: RestaurantListComponent, data: { role: Role[Role.ROLE_USER] } },
  { path: 'restaurant/add', component: RestaurantFormComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_USER] } },
  { path: 'restaurant/edit/:id', component: RestaurantFormComponent, canActivate: [YourGuardGuard], data: { role: Role[Role.ROLE_USER] } },
  { path: 'restaurant/detail/:id', component: RestuarantDetailComponent },
  { path: '**', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }