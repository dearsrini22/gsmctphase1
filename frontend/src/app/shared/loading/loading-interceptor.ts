import {Injectable} from '@angular/core';
import {LoadingService} from "./loading.service";
import {LoadingOverlay} from "./loading-overlay";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {tap} from "rxjs/operators";

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {
    constructor(private loadingService: LoadingService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let loadingRef: LoadingOverlay;
        Promise.resolve(null).then(() => loadingRef = this.loadingService.open());

        return next.handle(req).pipe(tap((event: any) => {
            if (event instanceof HttpResponse && loadingRef) {
                loadingRef.close();
            }
        }), catchError((error: any) => {
            if (loadingRef) {
                loadingRef.close();
            }
            return throwError(() => error);
        }));
    }
}
