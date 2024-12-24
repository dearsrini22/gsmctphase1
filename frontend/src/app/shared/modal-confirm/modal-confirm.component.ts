import {Component, Input} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: 'app-modal-confirm',
    templateUrl: './modal-confirm.component.html',
    styleUrl: './modal-confirm.component.scss'
})
export class ModalConfirmComponent {
    constructor(public modal: NgbActiveModal) {
    }

    @Input()
    title: string = 'Delete Confirmation';

    @Input()
    body: string = 'Are you sure you want to delete?';
}
