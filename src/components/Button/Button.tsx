import styles from './Button.module.scss';

interface ButtonProps {
  children: React.ReactNode;
  onClick: () => void;
  variant: 'categoryBtn' | 'submitBtn' | 'todoBtn';
  disabled?: boolean;
}

export default function Button({children, onClick, disabled = false, variant = 'categoryBtn'}: ButtonProps) {
  const btnClass = `${styles.btn} ${styles[variant]}`;

  return (
    <button className={btnClass} onClick={onClick} disabled={disabled}>{children}</button>
  )
}
