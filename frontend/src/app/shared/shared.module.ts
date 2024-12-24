import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MaterialModule} from '../material/material.module';
import {LoadingComponent} from './loading/loading/loading.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {LoadingInterceptor} from "./loading/loading-interceptor";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {LoadingService} from "./loading/loading.service";
import {NgxMatSelectSearchModule} from "ngx-mat-select-search";
import {MatSelectModule} from "@angular/material/select";

@NgModule({
    declarations: [
        LoadingComponent
    ],
    imports: [CommonModule, MaterialModule, MatProgressSpinnerModule, MatSelectModule, NgxMatSelectSearchModule],
    providers: [
        LoadingService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: LoadingInterceptor,
            multi: true
        }
    ]
})
export class SharedModule {
}
