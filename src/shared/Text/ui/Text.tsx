import { classes, Mods } from "resources/lib/classNames/classes";
import { memo } from "react";
import { ETextTheme, ETextAlign, ETextSize } from "../consts";
import cls from "./Text.module.scss";

type THeaderTag = "h1" | "h2" | "h3" | "h4" | "h5" | "h6";

interface ITextProps {
  className?: string;
  title?: string;
  text?: string;
  theme?: ETextTheme;
  textAlign?: ETextAlign;
  textSize?: ETextSize;

  "data-testid"?: string;
}

export const Text = memo(({
  className,
  text,
  title,
  theme = ETextTheme.PRIMARY,
  textAlign = ETextAlign.CENTER,
  textSize = ETextSize.M,
  "data-testid": dataTestId
}: ITextProps) => {
  const headerMapping: Record<ETextSize, THeaderTag> = {
    [ETextSize.XXS]: "h6",
    [ETextSize.XS]: "h5",
    [ETextSize.S]: "h4",
    [ETextSize.M]: "h3",
    [ETextSize.L]: "h2",
    [ETextSize.XL]: "h1",
  };

  const Header = headerMapping[textSize];

  const mods: Mods = {
    [cls[theme]]: true,
    [cls[textAlign]]: true,
    [cls[textSize]]: true,
  };

  return (
    <>
      {title && (
      <Header
        data-testid={`${dataTestId}.Title`}
        className={classes(cls.title, mods, [className])}
      >
        {title}
      </Header>
      )}
      {text && (
      <p
        data-testid={`${dataTestId}.Text`}
        className={classes(cls.text, mods, [className])}
      >
        {text}
      </p>
      )}
    </>
  );
});
