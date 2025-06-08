import { useTranslation } from "react-i18next";
import { memo, useCallback } from "react";
import { ListBox } from "shared/MenuListbox/ListBox";
import { Text, ETextSize } from "shared/Text";
import cls from "./Currency.module.scss";
import { ECurrency } from "../../model/consts/currency";

interface ICurrencyProps {
  className?: string;
  defaultValue?: ECurrency;
  isReadOnly?: boolean;
  onChange?: (value: ECurrency) => void;
}

const optionsList = [
  { value: ECurrency.EUR, content: ECurrency.EUR },
  { value: ECurrency.BYN, content: ECurrency.BYN },
  { value: ECurrency.RUB, content: ECurrency.RUB },
  { value: ECurrency.USD, content: ECurrency.USD },
];

export const Currency = memo(({ className, defaultValue, isReadOnly, onChange }: ICurrencyProps) => {
  const { t } = useTranslation();

  const onChangeHandler = useCallback((value: string) => {
    onChange?.(value as ECurrency);
  }, [onChange]);

  return (
    <>
      <Text
        textSize={ETextSize.S}
        text={t("Валюта")}
      />

      <ListBox
      // className={classes(cls.Currency, {}, [className])}
        value={defaultValue}
        defValue={t("Валюта")}
        readonly={isReadOnly}
        onChange={onChangeHandler}
        items={optionsList}
        direction="top"
      />
    </>
  );
  // return (
  //   <Select
  //     className={classes(cls.Currency, {}, [className])}
  //     defaultValue={defaultValue}
  //     title={t("Валюта")}
  //     readonly={isReadOnly}
  //     onChange={onChangeHandler}
  //     optionsList={optionsList}
  //   />
  // );
});
