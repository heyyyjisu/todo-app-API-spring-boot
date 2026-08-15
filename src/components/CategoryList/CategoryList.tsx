import React, { useState } from 'react'
import Button from '../Button/Button'

export default function CategoryList() {

  const [value, setValue] = useState("");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  }

  const handleClick = async () => {
    try {
      const res = await fetch('http://localhost:8080/categories', {
        method: "POST",
        headers: {
          "Content-Type": 'application/json',
        },
        body: JSON.stringify({
          name: value,
        }),
      });
      if (!res.ok) {
        throw new Error (`HTTP error! Status: ${res.status}`)
      }
      const data = await res.json();
      console.log(data);
      setValue("");
    } catch (err) {
      if (err instanceof Error) {
        console.error(err.message);
      } else {
        console.error(err);
      }
    }
  }

  return (
    <div>
      <input type="text" placeholder='type here...' value={value} onChange={handleChange}  />
      <Button onClick={handleClick}>Submit</Button>
    </div>
  )
}
