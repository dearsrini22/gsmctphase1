import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router, RouterLink} from "@angular/router";
import {AssociationService} from "../service/association.service";
import {ModalConfirmComponent} from "../shared/modal-confirm/modal-confirm.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastrService} from "ngx-toastr";
import {Association} from "../model/association";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [CommonModule, RouterLink],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
    associationList: any = [];

    constructor(private router: Router, private associationService: AssociationService, private modalService: NgbModal,
                private toaster: ToastrService) {
    }

    ngOnInit(): void {
        this.getAllAssociations().then(r => console.log());
    }

    async getAllAssociations() {
        this.associationService.searchAssociation().subscribe((data: any) => {
                if (data != null && data.body != null) {
                    const resultData = data.body;
                    if (resultData) {
                        this.associationList = resultData.content;
                    }
                }
            },
            (error: any) => {
                if (error) {
                    if (error.status == 404) {
                        if (error.error && error.error.message) {
                            this.associationList = [];
                        }
                    }
                }
            });
    }

    addAssociation() {
        this.router.navigate(['add-association']).then(r => console.log(r));
    }

    deleteAssociation(association: Association) {
        this.modalService.open(ModalConfirmComponent, {
                ariaLabelledBy: 'modal-basic-title',
                centered: true
            }).result.then(() => {
                this.associationService.deleteAssociation(<number>association.id).subscribe({
                    next: data => {
                        if (data && data.status == 204)
                            this.toaster.success('Successfully deleted Association - "' + association.name + '"');
                        else
                            this.toaster.error('Failed deleting Association - "' + association.name + '"');
                        this.getAllAssociations().then(r => console.log());
                    }, error: () => {
                        this.toaster.error('Failed deleting Association - "' + association.name + '"');
                        this.getAllAssociations().then(r => console.log());
                    }
                })
            },
            (reason) => {
            });
    }
}
