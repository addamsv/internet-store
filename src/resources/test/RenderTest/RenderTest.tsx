import { render } from "@testing-library/react";
import { IStateSchema, StoreProvider } from "resources/store/StoreProvider";
import { ReactNode } from "react";
import { I18nextProvider } from "react-i18next";
import { MemoryRouter } from "react-router-dom";
import i18nForTests from "resources/config/i18n/i18nForTests";
import { ReducersMapObject } from "@reduxjs/toolkit";

export interface IRenderTest {
  route?: string;
  initialState?: DeepPartial<IStateSchema>;
  asyncReducers?: DeepPartial<ReducersMapObject<IStateSchema>>;
}

export const RenderTest = (Component: ReactNode, options: IRenderTest = {}) => {
  const { route = "/", initialState, asyncReducers } = options;

  return render(
    <MemoryRouter initialEntries={[route]}>
      <StoreProvider asyncReducers={asyncReducers} initialState={initialState}>
        <I18nextProvider i18n={i18nForTests}>
          {Component}
        </I18nextProvider>
      </StoreProvider>
    </MemoryRouter>
  );
};
