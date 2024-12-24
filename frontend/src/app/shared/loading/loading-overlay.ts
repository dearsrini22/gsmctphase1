import {OverlayRef} from "@angular/cdk/overlay";

export class LoadingOverlay {
  constructor(private overlayRef: OverlayRef) { }

  close(): void {
    this.overlayRef.dispose();
  }
}
