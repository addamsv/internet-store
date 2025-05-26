import { fireEvent, render, screen } from "@testing-library/react";
import { RenderTest } from "resources/test/RenderTest/RenderTest";
import { IProfile } from "entities/Profile";
import { ECountry } from "entities/Country";
import { ECurrency } from "entities/Currency";
import userEvent from "@testing-library/user-event";
import { AXIOS } from "resources/lib/restApi/AXIOS";
import { profileReducer } from "../../model/slice/profileSlice";
import { EditProfileCard } from "./EditProfileCard";

const profile: IProfile = {
  owner: 1,
  firstname: "string",
  lastname: "string",
  age: 21,
  country: ECountry.Belarus,
  city: "Minsk",
  address: "Address",
  image: "http://string",
  currency: ECurrency.BYN
};

const testOptions = {
  initialState: {
    profile: {
      isReadOnly: true,
      data: profile,
      editedData: profile
    },
    user: {
      authData: { user: { id: 1, name: "admin" } }
    }
  },
  asyncReducers: {
    profile: profileReducer
  }
};

describe("Проверка компонента EditProfileCard", () => {
  test("тест на присутствие компонента EditProfileCard", async () => {
    RenderTest(<EditProfileCard id="1" />, testOptions);

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Edit"));

    expect(screen.getByTestId("EditProfileCard.Button.Cancel")).toBeInTheDocument();
  });

  test("тест на отмену изменений в компоненте EditProfileCard", async () => {
    RenderTest(<EditProfileCard id="1" />, testOptions);

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Edit"));
    await userEvent.clear(screen.getByTestId("EditProfileCard.Input.Firstname"));
    await userEvent.clear(screen.getByTestId("EditProfileCard.Input.Lastname"));
    await userEvent.type(screen.getByTestId("EditProfileCard.Input.Firstname"), "fake-data");
    await userEvent.type(screen.getByTestId("EditProfileCard.Input.Lastname"), "fake-data");

    expect(screen.getByTestId("EditProfileCard.Input.Firstname")).toHaveValue("fake-data");
    expect(screen.getByTestId("EditProfileCard.Input.Lastname")).toHaveValue("fake-data");

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Cancel"));

    expect(screen.getByTestId("EditProfileCard.Input.Firstname")).toHaveValue("string");
    expect(screen.getByTestId("EditProfileCard.Input.Lastname")).toHaveValue("string");
  });

  test("тест на валидацию изменений в компоненте EditProfileCard", async () => {
    RenderTest(<EditProfileCard id="1" />, testOptions);

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Edit"));

    await userEvent.clear(screen.getByTestId("EditProfileCard.Input.Firstname"));

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Save"));

    expect(screen.getByTestId("EditProfileCardError.Title")).toBeInTheDocument();
  });

  test("тест на success изменений в компоненте EditProfileCard м что PUT запрос ушёл", async () => {
    const mockedPUTRequest = jest.spyOn(AXIOS, "put");

    RenderTest(<EditProfileCard id="1" />, testOptions);

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Edit"));

    await userEvent.type(screen.getByTestId("EditProfileCard.Input.Firstname"), "Newman");
    await userEvent.type(screen.getByTestId("EditProfileCard.Input.Lastname"), "Baddy");

    await userEvent.click(screen.getByTestId("EditProfileCard.Button.Save"));

    expect(mockedPUTRequest).toHaveBeenCalled();
  });
});
