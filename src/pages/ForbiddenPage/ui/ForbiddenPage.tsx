import { Counter } from "entities/Counter";
import React from "react";
import { useTranslation } from "react-i18next";
import { Card } from "shared/Card/Card";
import { VFlex } from "shared/Flex/VFlex";
import { Page } from "widgets/Page/Page";
import cls from "./ForbiddenPage.module.scss";

const ForbiddenPage = () => {
  const { t } = useTranslation("");
  return (
    <Page>
      <Card className={cls.wrapper}>
        <VFlex gap="8">
          <h2 className="App-link">{t("Forbidden")}</h2>
        </VFlex>
      </Card>
    </Page>
  );
};

export default ForbiddenPage;
