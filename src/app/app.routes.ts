import { Routes } from '@angular/router';
import { ProductsComponent } from './pages/products/products.component';
import { AboutComponent } from './pages/about/about.component';
import { CartComponent } from './pages/cart/cart.component';
import { CategoriesComponent } from './pages/categories/categories.component';
import { ContactComponent } from './pages/contact/contact.component';
import { FAQComponent } from './pages/FAQ/faq.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { InfoproductsComponent } from './pages/infoproducts/infoproducts.component';
import {ProductsAdminComponent} from './pagesAdmin/products-admin/products-admin.component';
import { CategoriesAdminComponent } from './pagesAdmin/categories-admin/categories-admin.component';
import { BrandsAdminComponent } from './pagesAdmin/brands-admin/brands-admin.component';


export const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch:'full'},
    {path:'products', component: ProductsComponent},
    {path: ':categoryName/products', component: ProductsComponent},
    {path: ':categoryName/products/:idProduct', component: InfoproductsComponent},
    {path:'categories', component: CategoriesComponent},
    {path:'about', component: AboutComponent},
    {path:'cart', component: CartComponent},
    {path:'contact', component: ContactComponent},
    {path:'FAQ', component: FAQComponent},
    {path:'home', component: HomeComponent},
    {path:'login', component: LoginComponent},
    {path: 'admin/dashboard', component: ProductsAdminComponent},
    {path: 'admin/Categories', component: CategoriesAdminComponent},
    {path: 'admin/Brands', component: BrandsAdminComponent}
];
