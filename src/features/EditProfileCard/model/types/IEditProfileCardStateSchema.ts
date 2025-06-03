import { IProfile } from "entities/Profile";
import { EnumValidateProfileErrs } from "../consts";

export interface IProfileStateSchema {
  data?: IProfile;
  isLoading?: boolean;
  error?: string;
  isReadOnly?: boolean;

  editedData?: IProfile;
  validationError?: EnumValidateProfileErrs[];
}
