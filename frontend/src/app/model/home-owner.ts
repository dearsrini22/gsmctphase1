import {State} from "./state";
import {Association} from "./association";

export class HomeOwner {
  id?: number;
  association: Association | undefined;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  email?: string;
  phone?: string;
  mobileNumber?: string;
  address1?: string;
  address2?: string;
  city?: string;
  zipCode?: string;
  state?: string;
  associationId?: string;
}
