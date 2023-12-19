//
//  DatePickerDialog.swift
//  qrgrandClones
//
//  Created by LuanLQ on 04/12/2023.
//

import SwiftUI

struct DatePickerDialog: View {
    @Binding private var isShowing: Bool
    @Binding private var selection: Date

    private var internalSelection: State<Date>

    private var limitRange: ClosedRange<Date> {
        let currentDate: Date = .now

        let startDate = Calendar.current.date(
            from: DateComponents(year: 2015))!
        let startEnd = Calendar.current.date(
            byAdding: .year, value: 10, to: currentDate)!
        return startDate ... startEnd
    }

    init(
        isShowing: Binding<Bool>,
        selection: Binding<Date>
    ) {
        _isShowing = isShowing
        _selection = selection
        self.internalSelection = State(initialValue: selection.wrappedValue)
    }

    var body: some View {
        VStack {
            DatePicker("",
                       selection: self.internalSelection.projectedValue,
                       in: self.limitRange,
                       displayedComponents: .date
            )
            .labelsHidden()
            .datePickerStyle(.graphical)
            HStack {
                Button("キャンセル") {
                    self.isShowing = false
                }
                .font(AppFont.medium(16))
                .foregroundColor(primary)
                .frame(maxWidth: .infinity)

                Button("Ok") {
                    self.selection = self.internalSelection.wrappedValue
                    self.isShowing = false
                }
                .font(AppFont.medium(16))
                .foregroundColor(primary)
                .frame(maxWidth: .infinity)
            }
            .padding(.top, 10)
        }
        .padding()
    }
}

extension View {
    func datePickerDialog(
        isShowing: Binding<Bool>,
        selection: Binding<Date>
    ) -> some View {
        self.genericDialog(isShowing: isShowing) {
            DatePickerDialog(
                isShowing: isShowing,
                selection: selection
            )
        }
    }
}

struct DatePickerDialogTest: View {
    @State private var isShowing: Bool = false
    @State private var date: Date = .now

    var body: some View {
        VStack {
            Text(self.date.formatted())

            Spacer()

            Button("Date Picker") {
                self.isShowing = true
            }

            Spacer()
        }
        .datePickerDialog(
            isShowing: self.$isShowing,
            selection: self.$date
        )
    }
}

struct DatePickerDialogTest_Previews: PreviewProvider {
    static var previews: some View {
        DatePickerDialogTest()
    }
}
