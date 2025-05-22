import { classes } from "resources/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { memo } from "react";
import { HFlex } from "shared/Flex/HFlex";
import { VFlex } from "shared/Flex/VFlex";
import { Text, TextAlign, TextSize } from "shared/Text";
import { Card } from "shared/Card/Card";
import { useHelpStepsList } from "../../api/helpStepsAPI";
import cls from "./HelpStepsList.module.scss";
import { IHelpStepItem } from "../../types";

const render = (item: IHelpStepItem) => {
  if (!item.id) {
    return null;
  }

  const baseURL = `${__REST_API__BASE_URL__}`;

  return (
    <VFlex key={item.id}>
      <Text text={item.title} />
      <Text text={item.description} />
      <img key={item.link} width={280} height={480} src={`${baseURL}${item.link}`} alt={item.title} />
    </VFlex>
  );
};

export const HelpStepsList = memo(() => {
  const { data, isLoading, error } = useHelpStepsList(undefined);

  if (isLoading || error || !data) {
    return null;
  }

  const { data: list } = data;

  return (

    <Card className={cls.contentWrapper}>
      <VFlex gap="8">

        <Text textAlign={TextAlign.LEFT} textSize={TextSize.L} title="FAQ" />

        <Text textAlign={TextAlign.LEFT} text="How to download a link?" />

        <HFlex className={cls.flexWrap} gap="16">
          {list.length ? list.map(render) : null}
        </HFlex>

      </VFlex>
    </Card>
  );
});
