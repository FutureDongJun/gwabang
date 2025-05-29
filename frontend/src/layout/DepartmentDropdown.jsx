import { useState, useEffect } from "react";
import { Combobox } from "@headlessui/react";
import { CheckIcon, ChevronUpDownIcon } from "@heroicons/react/20/solid";
import axios from "axios";

export default function DepartmentDropdown({ selected, setSelected }) {
  const [query, setQuery] = useState("");
  const [departments, setDepartments] = useState([]);

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_API_URL}/api/departments`)
      .then((res) => {
        console.log("서버 응답:", res.data); // 이걸 확인하세요
        setDepartments(res.data);
      })
      .catch((err) => console.error("학과 리스트 가져오기 실패", err));
  }, []);

  const filtered =
    query === ""
      ? departments
      : departments.filter((dept) =>
          dept.toLowerCase().includes(query.toLowerCase())
        );

  return (
    <div className="w-full">
      <Combobox value={selected} onChange={setSelected}>
        <div className="relative">
          <div className="relative w-full cursor-default overflow-hidden rounded-lg bg-white text-left shadow-md focus:outline-none sm:text-sm">
            <Combobox.Input
              className="w-full border-none py-2 pl-3 pr-10 text-sm leading-5 text-gray-900 focus:ring-0"
              placeholder="학과 검색"
              onChange={(event) => setQuery(event.target.value)}
              displayValue={() => selected}
            />
            <Combobox.Button className="absolute inset-y-0 right-0 flex items-center pr-2">
              <ChevronUpDownIcon
                className="h-5 w-5 text-gray-400"
                aria-hidden="true"
              />
            </Combobox.Button>
          </div>
          {filtered.length > 0 && (
            <Combobox.Options className="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
              {filtered.map((dept) => (
                <Combobox.Option
                  key={dept}
                  value={dept}
                  className={({ active }) =>
                    `relative cursor-default select-none py-2 pl-10 pr-4 ${
                      active ? "bg-blue-600 text-white" : "text-gray-900"
                    }`
                  }
                >
                  {({ selected, active }) => (
                    <>
                      <span
                        className={`block truncate ${
                          selected ? "font-medium" : "font-normal"
                        }`}
                      >
                        {dept}
                      </span>
                      {selected && (
                        <span
                          className={`absolute inset-y-0 left-0 flex items-center pl-3 ${
                            active ? "text-white" : "text-blue-600"
                          }`}
                        >
                          <CheckIcon className="h-5 w-5" aria-hidden="true" />
                        </span>
                      )}
                    </>
                  )}
                </Combobox.Option>
              ))}
            </Combobox.Options>
          )}
        </div>
      </Combobox>
    </div>
  );
}
