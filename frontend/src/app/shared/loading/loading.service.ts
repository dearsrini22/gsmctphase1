import {ComponentRef, Injectable, Injector} from '@angular/core';
import {Overlay, OverlayConfig, OverlayRef} from "@angular/cdk/overlay";
import {LoadingOverlay} from "./loading-overlay";
import {LoadingComponent} from "./loading/loading.component";
import {ComponentPortal} from "@angular/cdk/portal";

@Injectable({
    providedIn: 'root'
})
export class LoadingService {

    constructor(private injector: Injector, private overlay: Overlay) {
    }

    open(): LoadingOverlay {
        const overlayRef = this.createOverlay();
        const dialogRef = new LoadingOverlay(overlayRef);
        const overlayComponent = this.attachDialogContainer(overlayRef, dialogRef);
        return dialogRef;
    }

    private createOverlay(): OverlayRef {
        const positionStrategy = this.overlay
            .position()
            .global()
            .centerHorizontally()
            .centerVertically();
        const overlayConfig = new OverlayConfig({
            hasBackdrop: true,
            scrollStrategy: this.overlay.scrollStrategies.block(),
            positionStrategy
        });

        return this.overlay.create(overlayConfig);
    }

    private attachDialogContainer(overlayRef: OverlayRef, dialogRef: LoadingOverlay): LoadingComponent {
        const injector = this.createInjector(dialogRef);
        const containerPortal = new ComponentPortal(LoadingComponent, null, injector);
        const containerRef: ComponentRef<LoadingComponent> = overlayRef.attach(containerPortal);
        return containerRef.instance;
    }

    private createInjector(dialogRef: LoadingOverlay): Injector {
        return Injector.create({
            parent: this.injector,
            providers: [
                {provide: LoadingOverlay, useValue: dialogRef}
            ]
        })
    }
}
